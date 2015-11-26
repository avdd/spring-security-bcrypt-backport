
package careflight.adhoc;

import java.util.regex.Pattern;
import org.springframework.security.providers.encoding.PasswordEncoder;

public class BCryptPasswordEncoder
implements PasswordEncoder
{

    private Pattern BCRYPT_PATTERN =
        Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    public String encodePassword(String rawPass, Object unused) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(rawPass, salt);
    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        if (encPass == null || encPass.length() == 0)
            return false;
        if (!BCRYPT_PATTERN.matcher(encPass).matches())
            return false;
        return BCrypt.checkpw(rawPass, encPass);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        String out = "bad args";
        if (args.length == 0)
            out = BCrypt.gensalt();
        else if (args.length == 1)
            out = e.encodePassword(args[0], null);
        else if (args.length == 2)
            out = new Boolean(e.isPasswordValid(args[1], args[0], null)).toString();
        System.out.println(out);
    }
}

