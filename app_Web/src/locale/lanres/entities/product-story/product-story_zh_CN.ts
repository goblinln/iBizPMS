import ProductStory_zh_CN_Base from './product-story_zh_CN_base';

function getLocaleResource(){
    const ProductStory_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductStory_zh_CN_Base(), ProductStory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;