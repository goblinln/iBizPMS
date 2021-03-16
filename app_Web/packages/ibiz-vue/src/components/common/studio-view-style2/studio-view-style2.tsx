import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators/VueLifeCycleProcessing';
import { StudioViewStyle2Base } from './studio-view-style2-base';
import './studio-view-style2.less';

/**
 * 视图布局模式2
 *
 * @export
 * @class StudioViewStyle2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class StudioViewStyle2 extends StudioViewStyle2Base {}
