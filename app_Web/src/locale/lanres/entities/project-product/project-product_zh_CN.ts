import ProjectProduct_zh_CN_Base from './project-product_zh_CN_base';

function getLocaleResource(){
    const ProjectProduct_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectProduct_zh_CN_Base(), ProjectProduct_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;