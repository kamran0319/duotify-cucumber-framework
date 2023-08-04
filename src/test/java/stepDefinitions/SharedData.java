package stepDefinitions;


import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

@Data
public class SharedData {

    private String password;
    private String username;
    private String randomEmail;
    private String randomPlaylistName;
    private String first;
    private String last;
    private String email;
    private String passMD5;
    private List<String> dbColumnNames;
    private List<String> emailsColumn;



}
