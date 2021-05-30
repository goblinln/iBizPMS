import ProjectTestTask_en_US_Base from './project-test-task_en_US_base';

function getLocaleResource(){
    const ProjectTestTask_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTestTask_en_US_Base(), ProjectTestTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;