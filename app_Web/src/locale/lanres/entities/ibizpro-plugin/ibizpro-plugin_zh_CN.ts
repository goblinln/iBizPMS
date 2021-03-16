import IBIZProPlugin_zh_CN_Base from './ibizpro-plugin_zh_CN_base';

function getLocaleResource(){
    const IBIZProPlugin_zh_CN_OwnData = {};
    const targetData = Object.assign(IBIZProPlugin_zh_CN_Base(), IBIZProPlugin_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;