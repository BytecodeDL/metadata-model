package org.clyze.persistent.model;

import java.util.Map;

/**
 * A named item that can be serialized as JSON.
 */
public interface Item {

	/**
	 * Get the item id.
	 * @return The item id
	 */
	String getId();

	/**
	 * Set the item id.
	 * @param id   the item id
	 */
	void setId(String id);	

	/**
	 * Instantiate an item from JSON data.
	 * @param json   the JSON data to populate the object
	 * @return		 the instance that corresponds to the JSON data
	 */
	Item fromJSON(String json);

	/**
	 * Serialize the item as JSON.
	 * @return the object state in JSON representation
	 */
	String toJSON();

	/**
	 * Serialize the item as a map.
	 * @return the object state as a map from item properties to property values
	 */
	Map<String, Object> toMap();

	/**
	 * Instantiate an item from a map.
	 * @param map    the map to use to populate the object
	 */
	void fromMap(Map<String, Object> map);
}
