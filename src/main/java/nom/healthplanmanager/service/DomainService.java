package nom.healthplanmanager.service;

import nom.healthplanmanager.dto.DataTransferObject;
import nom.healthplanmanager.model.Domain;

import java.lang.reflect.ParameterizedType;
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
            DOMAIN domain = domainClass.getDeclaredConstructor().newInstance();
            domain.setId(dto.getId());
            return domain;
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to determine the generic type of the domain class.", ex);
        }
    }


    protected DTO parse(DOMAIN domain){
        try{
            DTO dto = dtoClass.getDeclaredConstructor().newInstance();
            dto.setId(domain.getId());
            return dto;
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to determine the generic type of the dto class.", ex);
        }

    }

}
