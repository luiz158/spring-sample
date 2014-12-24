package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.ents.Account;
import sample.repo.AccountRepository;
import sample.util.AccountSearchCriteria;

import javax.validation.Valid;
import java.util.List;

/**
 * Controls the execution of the user operation to search for accounts.
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountSearchController {

    private AccountRepository accountRepo;

    @Autowired
    public AccountSearchController(AccountRepository accoutnRepo) {
        this.accountRepo = accoutnRepo;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(AccountSearchCriteria criteria) {
        return "accounts/search";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String processSubmit(@Valid AccountSearchCriteria criteria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "accounts/search";
        }
        List<Account> accounts = accountRepo.findByName(criteria);
        if (accounts.size() == 1) {
            return "redirect:accounts/" + accounts.get(0).getId();
        } else {
            model.addAttribute("accountList", accounts);
            return "accounts/list";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showcreate(Model model) {
        model.addAttribute("account", new Account());
        return "accounts/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("account") @Valid Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(account);
            return "accounts/create";
        }
        accountRepo.save(account);
        return "redirect:" + account.getId();
    }


}
