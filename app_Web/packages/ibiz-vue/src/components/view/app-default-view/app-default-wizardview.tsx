import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWizardViewBase } from '../app-common-view/app-wizardview-base';

@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWizardView extends AppWizardViewBase { }