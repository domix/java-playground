package domix.fun.test.sample;

import codes.domix.fun.Result;

public interface UserRepository {
    Result<User, String> save(CreateUserCommand command);
}
