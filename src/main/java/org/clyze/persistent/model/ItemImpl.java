package org.clyze.persistent.model;

import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/** The top-level implementation of items in the model. */
public abstract class ItemImpl implements Item {

	protected String id; //The unique identifier of the item (it is optional and not serialized)

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public ItemImpl fromJSON(String json) {
		try {
			Map<String, Object> map = (Map<String, Object>)new ObjectMapper().readValue(json, Map.class);
			fromMap(map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this;
	}

	@Override
	public String toJSON() {
		try {
			return (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(toMap());
		} catch (Exception ex) {
			ex.printStackTrace();
			return "{}";
		}
	}

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		saveTo(map);		
		return map;
	}

	protected abstract void saveTo(Map<String, Object> map);
}
