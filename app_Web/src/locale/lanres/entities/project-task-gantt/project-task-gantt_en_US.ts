import ProjectTaskGantt_en_US_Base from './project-task-gantt_en_US_base';

function getLocaleResource(){
    const ProjectTaskGantt_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTaskGantt_en_US_Base(), ProjectTaskGantt_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;