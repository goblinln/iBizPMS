import ProjectTaskEstimate_en_US_Base from './project-task-estimate_en_US_base';

function getLocaleResource(){
    const ProjectTaskEstimate_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTaskEstimate_en_US_Base(), ProjectTaskEstimate_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;