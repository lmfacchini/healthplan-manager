package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.DataTransferObject;
import nom.healthplanmanager.model.Domain;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

public abstract class DomainService<DOMAIN extends Domain, DTO extends DataTransferObject> {


    private final Class<DOMAIN> domainClass;

    private final Class<DTO> dtoClass;

    public DomainService() {

        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type[] typeArgs = ((ParameterizedType) superClass).getActualTypeArguments();
            domainClass = (Class<DOMAIN>) typeArgs[0];
            dtoClass = (Class<DTO>) typeArgs[1];
        } else {
            throw new IllegalArgumentException("Não foi possível determinar o tipo genérico T.");
        }

    }

    protected DOMAIN parse(DTO dto){
        try{
            return domainClass.getDeclaredConstructor().newInstance();
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to determine the generic type of the domain class.", ex);
        }
    }


    protected DTO parse(DOMAIN domain){
        try{
            return dtoClass.getDeclaredConstructor().newInstance();
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to determine the generic type of the dto class.", ex);
        }

    }

    protected <T extends Collection<DTO>>T parse(Collection<DOMAIN> collection){
        if(collection == null){
            throw new IllegalArgumentException("Collection cannot be null.");
        }
        try{
            final T result = (T) collection.getClass().getDeclaredConstructor().newInstance();
            return collection.stream().map(this::parse).collect(Collectors.toCollection(()->result));
        }catch (Exception ex){
            throw new IllegalArgumentException("Collection cannot be instantiated.", ex);
        }
    }
}
