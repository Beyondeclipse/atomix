/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.raft.rpc;

import net.kuujo.copycat.io.util.ReferenceManager;

import java.util.function.Function;

/**
 * Client response.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public abstract class ClientResponse<T extends ClientResponse<T>> extends AbstractResponse<T> {
  protected long version;

  public ClientResponse(ReferenceManager<T> referenceManager) {
    super(referenceManager);
  }

  /**
   * Returns the response version.
   *
   * @return The response version.
   */
  public long version() {
    return version;
  }

  /**
   * Client response builder.
   */
  public static abstract class Builder<T extends Builder<T, U>, U extends ClientResponse<U>> extends AbstractResponse.Builder<T, U> {
    protected Builder(Function<ReferenceManager<U>, U> factory) {
      super(factory);
    }

    @Override
    @SuppressWarnings("unchecked")
    T reset() {
      super.reset();
      response.version = 0;
      return (T) this;
    }

    /**
     * Sets the query response version.
     *
     * @param version The response version.
     * @return The response builder.
     */
    @SuppressWarnings("unchecked")
    public T withVersion(long version) {
      response.version = version;
      return (T) this;
    }
  }

}
