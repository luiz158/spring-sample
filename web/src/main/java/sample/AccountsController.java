package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.ents.Account;
import sample.repo.AccountRepository;

import javax.validation.Valid;

/**
 * User: jules
 * Date: 4/20/14
 */
@Controller
@RequestMapping("/accounts/{number}")
public class AccountsController {

    private AccountRepository accountrepo;

    @Autowired
    public AccountsController(AccountRepository accountrepo) {
        this.accountrepo = accountrepo;
    }

    @ModelAttribute
    protected Account findAccount(@PathVariable("number") Integer id) {
        return accountrepo.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "accounts/show";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit() {
        return "accounts/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(account);
            return "accounts/edit";
        }
        accountrepo.save(account);
        return "redirect:" + account.getId();
    }

}
