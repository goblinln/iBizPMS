package cn.ibizlab.pms.core.runtime;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.net.URL;

public class SystemModelService extends net.ibizsys.model.PSModelServiceImpl{

	/**
	 * 模型是否来自jar包
	 */
	private boolean bFromJar = true;
	
	@Override
	protected ObjectNode getObjectNode(String strPath) throws Exception {
		//替换模型，例如从类中获取
		if (isFromJar()) {
            strPath = this.getPSModelFolderPath() + File.separator + strPath;
            strPath = strPath.replace("\\", "/");
            return (ObjectNode) MAPPER.readTree(this.getClass().getClassLoader().getResourceAsStream(strPath));
        }else{
			strPath = this.getPSModelFolderPath() + File.separator + strPath;
			strPath = strPath.replace("\\", "/");
			if(strPath.indexOf("http:") == 0){
				return (ObjectNode) MAPPER.readTree(new URL(strPath).openStream());
			}
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
