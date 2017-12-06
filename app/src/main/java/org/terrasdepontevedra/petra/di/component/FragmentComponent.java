package org.terrasdepontevedra.petra.di.component;

import org.terrasdepontevedra.petra.di.module.AdapterModule;
import org.terrasdepontevedra.petra.di.module.FragmentModule;
import org.terrasdepontevedra.petra.di.module.PresenterModule;
import org.terrasdepontevedra.petra.di.scopes.FragmentScope;
import org.terrasdepontevedra.petra.ui.adventure.AdventureFragment;
import org.terrasdepontevedra.petra.ui.adventure.quiz.pager.QuizPagerFragment;
import org.terrasdepontevedra.petra.ui.adventure.quiz.question.QuizFragment;
import org.terrasdepontevedra.petra.ui.adventure.result.ResultFragment;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaConnectionFragment;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaMapFragment;
import org.terrasdepontevedra.petra.ui.center.list.CenterListFragment;
import org.terrasdepontevedra.petra.ui.gallery.GalleryFragment;
import org.terrasdepontevedra.petra.ui.gallery.ImageFragment;
import org.terrasdepontevedra.petra.ui.gallery.horizontal.GalleryHorizontalFragment;
import org.terrasdepontevedra.petra.ui.help.HelpFragment;
import org.terrasdepontevedra.petra.ui.itinerary.content.ItineraryDetailFragment;
import org.terrasdepontevedra.petra.ui.itinerary.header.ItineraryImageFragment;
import org.terrasdepontevedra.petra.ui.itinerary.list.ItinerariesListFragment;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapBoxFragment;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapFragment;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapGoogleFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapBoxFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapGoogleFragment;
import org.terrasdepontevedra.petra.ui.place.content.PlaceContentFragment;
import org.terrasdepontevedra.petra.ui.place.detail.PlaceDetailFragment;
import org.terrasdepontevedra.petra.ui.settings.SettingsFragment;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                FragmentModule.class,
                AdapterModule.class,
                PresenterModule.class
        }
)
public interface FragmentComponent {
    void inject(ItineraryDetailFragment itineraryDetailFragment);

    void inject(PlaceDetailFragment placeDetailFragment);

    void inject(ItineraryImageFragment itineraryImageFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(ItinerariesListFragment itinerariesListFragment);

    void inject(MapGoogleFragment mapGoogleFragment);

    void inject(MapBoxFragment mapBoxFragment);

    void inject(MapFragment mapFragment);

    void inject(PlaceContentFragment placeContentFragment);

    void inject(QuizFragment quizFragment);

    void inject(CenterListFragment centerListFragment);

    void inject(QuizPagerFragment quizPagerFragment);

    void inject(ResultFragment resultFragment);

    void inject(AdventureFragment adventureFragment);

    void inject(YincanaConnectionFragment yincanaConnectionFragment);

    void inject(YincanaMapFragment yincanaMapFragment);

    void inject(GalleryFragment galleryFragment);

    void inject(ImageFragment imageFragment);

    void inject(HelpFragment helpFragment);

    void inject(GalleryHorizontalFragment galleryHorizontalFragment);

    void inject(ItineraryMapFragment itineraryMapFragment);

    void inject(ItineraryMapBoxFragment itineraryMapBoxFragment);

    void inject(ItineraryMapGoogleFragment itineraryMapGoogleFragment);
}
