import IBIZProPlugin_en_US_Base from './ibizpro-plugin_en_US_base';

function getLocaleResource(){
    const IBIZProPlugin_en_US_OwnData = {};
    const targetData = Object.assign(IBIZProPlugin_en_US_Base(), IBIZProPlugin_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;