

@FunctionalInterface
public interface CreateAccountUseCase {
  record CreateAccountCommand(String email, String password, String name) {
  }
  String createAccount(CreateAccountCommand command);
}
