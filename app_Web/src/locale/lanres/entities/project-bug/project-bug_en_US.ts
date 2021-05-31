import ProjectBug_en_US_Base from './project-bug_en_US_base';

function getLocaleResource(){
    const ProjectBug_en_US_OwnData = {};
    const targetData = Object.assign(ProjectBug_en_US_Base(), ProjectBug_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;