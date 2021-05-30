import ProjectTeam_zh_CN_Base from './project-team_zh_CN_base';

function getLocaleResource(){
    const ProjectTeam_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTeam_zh_CN_Base(), ProjectTeam_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;