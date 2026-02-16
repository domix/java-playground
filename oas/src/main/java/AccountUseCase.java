
public interface AccountUseCase {
  String createAccount(String email, String password);
  String login(String email, String password);
  String logout();
  String getAccount();
  String updateAccount(String email, String password);
}
