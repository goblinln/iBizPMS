import Release_zh_CN_Base from './release_zh_CN_base';

function getLocaleResource(){
    const Release_zh_CN_OwnData = {};
    const targetData = Object.assign(Release_zh_CN_Base(), Release_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;