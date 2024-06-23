# wiseget

My personal Readwise Android Widget.

## Context

- I have a Readwise account and use Reader as my read-it-later app.
- I really enjoy the daily review feature from Readwise.
- I never open the Readwise app, e-mails, or notifications.
- I need a seamless way to consume quotes; otherwise, I won't read or remember them.

The most intuitive solution was to add a Readwise Widget to my Android home screen. However, Readwise does not have an official (or unofficial) widget for Android users:

- Readwise [released an official iOS Widget](https://readwise.io/changelog/ios-widget) in February 2021.
- Tedis Agolli released [Readise Widget](https://play.google.com/store/apps/details?id=grape.k.readwiseapp&hl=pt_BR&gl=US), last updated in November 2021. It was even [tweeted by Readwise's](https://x.com/TedisAgolli/status/1458566152666460169) official account. Unfortunately, it doesn't work on my (2021) phone, and [the source code doesn't appear to be public](https://github.com/TedisAgolli?tab=repositories&q=readwise&type=&language=&sort=).

## My Journey

I'll likely post a more detailed version elsewhere, but the `tl;dr` is that I got carried away experimenting with some Gen AI tools over the weekend. Even the name "wiseget" was suggested by ChatGPT.

Now, I have something that "works on my phone," which is all I wanted. There are still some features I might add in the near future, but keep in mind that this is an experimental, unpublished app.

## Setup

> Ensure you understand what you're doing, especially regarding Readwise Authentication and installing a third-party app on your phone.

### Development Tools

You will need to install Android Studio, which should take care of everything else.

### Readwise Authentication

Generate your Readwise Access token at https://readwise.io/access_token.

Search the code for `YOUR_PERSONAL_TOKEN_SHOULD_BE_HARDCODED_HERE` and hardcode your token there if you are comfortable doing so.

### Running on Your Device

You can follow the official guide to [Run apps on a hardware device](https://developer.android.com/studio/run/device).

## Known TODOs

> I don't intend to work on all of these, but feel free to contribute.

- **Security:**
  - [ ] Parametrize the access token within the application settings so it's not hardcoded.
  - [ ] Safely store the access token.
- **Things I want:**
  - [ ] Implement the daily review API.
  - [ ] Add round-robin logging for daily reviews.
- **Settings:**
  - [ ] Add options to choose between daily review or random quotes.
  - [ ] Implement auto-refresh options.
- **Usability:**
  - [ ] Make the refresh action intuitive.
  - [ ] Clean up, refactor, and document everything.
  - [ ] Add widget image to the repo.
  - [ ] Add someway to go to the Readwise URL to see the original quote/highlight.
- **UI Improvements:**
  - [ ] Fix title position.
  - [ ] Fix default widget size.
  - [ ] Implement a book cover-based dynamic background color.
  - [ ] Add widget preview.
  - [ ] Add app icon etc.
  - [ ] Change fonts etc.
  - References:
    - [Official Readwise iOS Widget](https://readwise.io/changelog/ios-widget)
    - Android [Readwise Widget](https://play.google.com/store/apps/details?id=grape.k.readwiseapp&hl=pt_BR&gl=US) app
    - [Share quote](https://blog.readwise.io/p/f8c0f71c-fe5f-4025-af57-f9f65c53fed7#howdoisharehighlights) option in Readwise. See an example [here](https://entreresource.com/wp-content/uploads/2022/09/Building-a-Second-Brain-.png).
