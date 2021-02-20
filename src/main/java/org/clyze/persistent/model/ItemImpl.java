package org.clyze.persistent.model;

import java.util.Map;
import java.util.HashMap;
import org.clyze.persistent.metadata.JSONUtil;

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
			Map<String, Object> map = JSONUtil.toMap(json);
			fromMap(map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this;
	}

	@Override
	public String toJSON() {
		try {
			return JSONUtil.getObjectWriter().writeValueAsString(toMap());
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
