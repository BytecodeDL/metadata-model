package org.clyze.persistent.model;

import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

/**
 * A software artifact of a project (such as a JAR or AAR file).
 * 
 * The artifact refers either to an application or a dependency (isDependency) and has an ArtifactKind.
 */
public class Artifact extends ItemImpl {

	/** The suggested algorithm to use when computing checksums for artifacts. */
	@SuppressWarnings("unused")
	public static final String CHECKSUM_ALGORITHM = "SHA1";

	protected String id;
	protected String name;
	protected ArtifactKind kind;
	protected boolean isDependency;
	protected String sourcesName;
	protected String checksum;
	protected long sizeInBytes;
	protected final Set<String> packages;
	protected String parentArtifactId;

	public Artifact(String id, String name, ArtifactKind kind, boolean isDependency,
					String sourcesName, String checksum, long sizeInBytes) {
		this(id, name, kind, isDependency, sourcesName, checksum, sizeInBytes, new HashSet<>(), null);
	}	

	public Artifact(String id, String name, ArtifactKind kind, boolean isDependency,
					String sourcesName, String checksum, long sizeInBytes,
					Set<String> packages, String parentArtifactId) {
		this.id = id;
		this.name = name;
		this.kind = kind;
		this.isDependency = isDependency;
		this.sourcesName = sourcesName;
		this.checksum = checksum;
		this.sizeInBytes = sizeInBytes;
		this.packages = packages;
		this.parentArtifactId = parentArtifactId;
	}	

	@Override
	protected void saveTo(Map<String, Object> map) {
		map.put("id", getId());
		map.put("name", getName());
		map.put("kind", this.kind.name());
		map.put("isDependency", isDependency());
		map.put("sourcesName", getSourcesName());
		map.put("checksum", getChecksum());
		map.put("sizeInBytes", getSizeInBytes());
		map.put("packages", getPackages());
		map.put("parentArtifactId", getParentArtifactId());
	}

	@Override
	public void fromMap(Map<String, Object> map){
		setId((String) map.get("id"));
		this.name             = (String) map.get("name");
		this.kind             = ArtifactKind.valueOf((String) map.get("kind"));
		this.isDependency     = (Boolean) map.get("isDependency");
		setSourcesName((String) map.get("sourcesName"));
		setChecksum((String) map.get("checksum"));
		this.sizeInBytes      = ((Number) map.get("sizeInBytes")).longValue();
		//noinspection unchecked
		this.packages.addAll((Collection<String>) map.get("packages"));
		setParentArtifactId((String) map.get("parentArtifactId"));
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public ArtifactKind getKind() {
		return kind;
	}

	public boolean isDependency() {
		return isDependency;
	}

	public Set<String> getPackages() {
		return this.packages;
	}

	public void setParentArtifactId(String parentArtifactId) {
		this.parentArtifactId = parentArtifactId;
	}

	public String getParentArtifactId() {
		return parentArtifactId;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getSourcesName() {
		return this.sourcesName;
	}

	public void setSourcesName(String sourcesName) {
		this.sourcesName = sourcesName;
	}
}
