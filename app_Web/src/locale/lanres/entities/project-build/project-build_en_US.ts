import ProjectBuild_en_US_Base from './project-build_en_US_base';

function getLocaleResource(){
    const ProjectBuild_en_US_OwnData = {};
    const targetData = Object.assign(ProjectBuild_en_US_Base(), ProjectBuild_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;