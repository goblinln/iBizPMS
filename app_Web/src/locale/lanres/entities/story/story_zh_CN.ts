import Story_zh_CN_Base from './story_zh_CN_base';

function getLocaleResource(){
    const Story_zh_CN_OwnData = {};
    const targetData = Object.assign(Story_zh_CN_Base(), Story_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;