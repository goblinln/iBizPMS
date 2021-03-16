import IBIZProKeyword_en_US_Base from './ibizpro-keyword_en_US_base';

function getLocaleResource(){
    const IBIZProKeyword_en_US_OwnData = {};
    const targetData = Object.assign(IBIZProKeyword_en_US_Base(), IBIZProKeyword_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;