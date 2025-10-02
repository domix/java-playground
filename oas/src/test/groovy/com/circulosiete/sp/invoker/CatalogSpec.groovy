package com.circulosiete.sp.invoker

import com.circulosiete.sp.client.api.CountryApi
import spock.lang.Specification

class CatalogSpec extends Specification {
  def 'foo'() {
    given:
      def apiClient = new ApiClient()
      def countryApi = new CountryApi(apiClient)
    when:
      def result = countryApi.countriesGet(null, null, null, null);
      println result
    then:
      result
      result.data.count
  }
}
