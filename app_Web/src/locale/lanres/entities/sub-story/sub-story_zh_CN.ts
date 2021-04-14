import SubStory_zh_CN_Base from './sub-story_zh_CN_base';

function getLocaleResource(){
    const SubStory_zh_CN_OwnData = {};
    const targetData = Object.assign(SubStory_zh_CN_Base(), SubStory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;