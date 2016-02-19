package com.ferdeveloper.presentacion.json;

/**
 *
 * @author fernandoarenasdev
 */
public interface JsonTransformer {

    String toJSON(Object data);

    <T> T fromJSON(String json, Class<T> clazz);
}
