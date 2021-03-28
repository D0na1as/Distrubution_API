package Distribution.API.base.Controller.Config;

import org.springframework.stereotype.Component;

public enum OrderStatus {
    pending,
    canceled,
    preparing,
    shipped,
    received,
    returned
}
