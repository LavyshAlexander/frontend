package controllers.admin

import play.api.mvc.Controller
import common.Logging
import controllers.AuthLogging
import tools._
import tools.charts._
import model.NoCache

object AnalyticsController extends Controller with Logging with AuthLogging {

  // We only do PROD analytics

  def kpis() = Authenticated { implicit request =>
    NoCache(Ok(views.html.kpis("PROD", Seq(
      PageviewsPerUserGraph,
      ReturnUsersPercentageByDayGraph,
      DaysSeenPerUserGraph,
      ActiveUserProportionGraph,
      ActiveUsersFourDaysFromSevenOrMoreGraph
    ))))
  }

  def pageviews() = Authenticated { implicit request =>
    NoCache(Ok(views.html.pageviews("PROD", Seq(
      PageviewsByCountryGeoGraph,
      PageviewsByDayGraph,
      NewPageviewsByDayGraph,
      PageviewsByBrowserTreeMapGraph,
      PageviewsByOperatingSystemTreeMapGraph
    ))))
  }

  def browsers() = Authenticated { implicit request =>
    NoCache(Ok(views.html.browsers("PROD",
      Analytics.getPageviewsByOperatingSystem(),
      Analytics.getPageviewsByBrowser(),
      Analytics.getPageviewsByOperatingSystemAndBrowser()
    )))
  }

  def abtests() = Authenticated { implicit request =>
    NoCache(Ok(views.html.abtests("PROD",
      SwipeAvgPageViewsPerSessionGraph,
      SwipeAvgSessionDurationGraph,
      FacebookMostReadPageViewsPerSessionGraph
    )))
  }
}
