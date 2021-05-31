import ProjectStory_zh_CN_Base from './project-story_zh_CN_base';

function getLocaleResource(){
    const ProjectStory_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectStory_zh_CN_Base(), ProjectStory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;