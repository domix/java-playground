public class Demo {

  static class DefaultImp implements  CreateAccountUseCase {

    @Override
    public String createAccount(CreateAccountCommand comm) {
      return "";
    }
  }

  void foo(CreateAccountUseCase useCase) {

  }

  public static void main(String[] args) {
    var demo = new Demo();
    demo.foo(new CreateAccountUseCase() {
      @Override
      public String createAccount(CreateAccountCommand command) {
        command.name();
        return "";
      }
    });
    demo.foo((e) -> "");
  }
}
