import ProjectModule_en_US_Base from './project-module_en_US_base';

function getLocaleResource(){
    const ProjectModule_en_US_OwnData = {};
    const targetData = Object.assign(ProjectModule_en_US_Base(), ProjectModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;