package pl.bodzioch.damian.common;

import java.io.Serializable;

record ErrorResponse(
        String error
) implements Serializable {
}
