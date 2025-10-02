package domix.fun.test.sample

import domix.fun.Result
import spock.lang.Specification

class UserServiceSpecs extends Specification {


  def "should validate command in a business service"() {
    given:
      def userRepository = Stub(UserRepository)
      def userService = new UserService(userRepository)
      def command = new CreateUserCommand('meh', 'bah')
    when:
      def result = userService.register(command)
    then:
      assert result.isError()
      assert result.getError() == 'Email inválido'
    when:
      command = new CreateUserCommand('valid_email@validhost.com', 'meh')
      result = userService.register(command)
    then:
      assert result.isError()
      assert result.getError() == 'Contraseña débil'
    when:
      def expectedError = "DB timeout"
      userRepository.save(_) >> Result.err(expectedError)
      def password = UUID.randomUUID().toString()
      command = new CreateUserCommand('valid_email@validhost.com', password)
      result = userService.register(command)
    then:
      assert result.isError()
      assert expectedError == result.getError()
    when:
      userRepository = Stub(UserRepository)
      userService = new UserService(userRepository)
      userRepository.save(_) >> Result.ok(new User(command.email(), command.password()))
      def result2 = userService.register(command)
    then:
      assert result2.isOk()
      assert result2.get().email() == command.email()
  }
}
