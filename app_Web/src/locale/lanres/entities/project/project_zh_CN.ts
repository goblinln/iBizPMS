import Project_zh_CN_Base from './project_zh_CN_base';

function getLocaleResource(){
    const Project_zh_CN_OwnData = {};
    const targetData = Object.assign(Project_zh_CN_Base(), Project_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;