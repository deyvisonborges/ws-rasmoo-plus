package com.cliente.rasmoo.plus.integrations;

import com.cliente.rasmoo.plus.integrations.impl.MailIntegrationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainIntegrationImplTest {
    private final MailIntegrationImpl mailIntegration;

    public MainIntegrationImplTest(MailIntegrationImpl mailIntegration) {
        this.mailIntegration = mailIntegration;
    }

    @Test
    void send() {
       mailIntegration.send("meuemail@gmail.com", "senhadoemail", "");
    }
}
