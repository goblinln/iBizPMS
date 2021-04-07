package cn.ibizlab.pms.core.runtime;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class SystemModelService extends net.ibizsys.model.PSModelServiceImpl{

	private boolean bFromJar = true;
	
	@Override
	protected ObjectNode getObjectNode(String strPath) throws Exception {
		//替换模型，例如从类中获取
		if(isFromJar()) {
			strPath = strPath.replace("\\", "/");
			return (ObjectNode) MAPPER.readTree(this.getClass().getClassLoader().getResourceAsStream(strPath));
		}
		return super.getObjectNode(strPath);
	}
	
	public void setFromJar(boolean bFromJar) {
		this.bFromJar = bFromJar;
	}
	
	public boolean isFromJar() {
		return this.bFromJar;
	}
	
}
