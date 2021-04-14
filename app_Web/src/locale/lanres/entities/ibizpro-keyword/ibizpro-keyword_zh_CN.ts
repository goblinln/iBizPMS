import IBIZProKeyword_zh_CN_Base from './ibizpro-keyword_zh_CN_base';

function getLocaleResource(){
    const IBIZProKeyword_zh_CN_OwnData = {};
    const targetData = Object.assign(IBIZProKeyword_zh_CN_Base(), IBIZProKeyword_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;