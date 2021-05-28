import ProjectTask_en_US_Base from './project-task_en_US_base';

function getLocaleResource(){
    const ProjectTask_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTask_en_US_Base(), ProjectTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;