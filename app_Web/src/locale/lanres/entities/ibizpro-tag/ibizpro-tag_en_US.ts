import IBIZProTag_en_US_Base from './ibizpro-tag_en_US_base';

function getLocaleResource(){
    const IBIZProTag_en_US_OwnData = {};
    const targetData = Object.assign(IBIZProTag_en_US_Base(), IBIZProTag_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;