package domix.fun.test.sample;

import dmx.fun.Result;

public interface UserRepository {
    Result<User, String> save(CreateUserCommand command);
}
