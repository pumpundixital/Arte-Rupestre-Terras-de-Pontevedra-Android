package org.terrasdepontevedra.petra.di.component;

import org.terrasdepontevedra.petra.di.module.ActivityModule;
import org.terrasdepontevedra.petra.di.module.AdapterModule;
import org.terrasdepontevedra.petra.di.module.PresenterModule;
import org.terrasdepontevedra.petra.di.scopes.ActivityScope;
import org.terrasdepontevedra.petra.ui.MainActivity;
import org.terrasdepontevedra.petra.ui.SplashActivity;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizActivity;
import org.terrasdepontevedra.petra.ui.adventure.score.ScoreActivity;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaActivity;
import org.terrasdepontevedra.petra.ui.center.detail.CenterDetailActivity;
import org.terrasdepontevedra.petra.ui.gallery.GalleryActivity;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.ui.wizard.WizardActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                AdapterModule.class,
                PresenterModule.class

        }
)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(ItineraryActivity itineraryActivity);

    void inject(SplashActivity splashActivity);

    void inject(QuizActivity quizActivity);

    void inject(CenterDetailActivity centerDetailActivity);

    void inject(ScoreActivity scoreActivity);

    void inject(YincanaActivity yincanaActivity);

    void inject(GalleryActivity galleryActivity);

    void inject(WizardActivity wizardActivity);
}
