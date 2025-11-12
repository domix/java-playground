package domix.fun.test.sample;

import codes.domix.fun.Result;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<CreateUserCommand, String> validateEmail(CreateUserCommand command) {
        if (command.email().contains("@")) {
            return Result.ok(command);
        }

        return Result.err("Email inválido");
    }

    public Result<CreateUserCommand, String> validatePassword(CreateUserCommand command) {
        return command.password().length() >= 8 ?
                Result.ok(command) :
                Result.err("Contraseña débil");
    }

    public Result<User, String> saveUser(CreateUserCommand command) {
        return userRepository.save(command);
    }

    public Result<User, String> register(CreateUserCommand command) {
        return validateEmail(command)
                .flatMap(this::validatePassword)
                .flatMap(this::saveUser);
    }
}

