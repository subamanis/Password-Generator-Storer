package manager.domain.jackson.modules;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import manager.domain.Account;
import manager.domain.DataRepository;
import manager.domain.jackson.deserializers.AccountDeserializer;
import manager.domain.jackson.deserializers.DataRepositoryDeserializer;
import manager.domain.jackson.deserializers.LocalDateTimeDeserializer;
import manager.domain.jackson.serializers.AccountSerializer;
import manager.domain.jackson.serializers.DataRepositorySerializer;
import manager.domain.jackson.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ApplicationModule extends SimpleModule {
    public ApplicationModule() {
        super("ApplicationModule", new Version(1, 0, 0, null, null, null));
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        addSerializer(Account.class, new AccountSerializer());
        addDeserializer(Account.class, new AccountDeserializer());

        addSerializer(DataRepository.class, new DataRepositorySerializer());
        addDeserializer(DataRepository.class, new DataRepositoryDeserializer());
    }
}
