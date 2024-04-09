package nom.healthplanmanager;

import java.io.Serializable;

public interface ValueObject<T extends Serializable> {

    void setId(T id);

    T getId();
}
