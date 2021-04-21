import IBIZProTag_zh_CN_Base from './ibizpro-tag_zh_CN_base';

function getLocaleResource(){
    const IBIZProTag_zh_CN_OwnData = {};
    const targetData = Object.assign(IBIZProTag_zh_CN_Base(), IBIZProTag_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;