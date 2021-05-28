import ProjectTaskGantt_zh_CN_Base from './project-task-gantt_zh_CN_base';

function getLocaleResource(){
    const ProjectTaskGantt_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTaskGantt_zh_CN_Base(), ProjectTaskGantt_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;