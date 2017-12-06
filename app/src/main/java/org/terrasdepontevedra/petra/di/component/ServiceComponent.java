package org.terrasdepontevedra.petra.di.component;

import org.terrasdepontevedra.petra.data.service.DownloadContentService;
import org.terrasdepontevedra.petra.di.module.UseCaseModule;
import org.terrasdepontevedra.petra.di.scopes.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                UseCaseModule.class
        }
)
public interface ServiceComponent {
    void inject(DownloadContentService downloadContentService);
}
